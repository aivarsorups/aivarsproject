package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.javaguru.shoppinglist.repository.ProductInMemoryRepository.MAX_PERCENT_FOR_DISCOUNT;

@Repository
@Profile("hibernate")
@Transactional
public class HibernateProductRepository implements ProductRepository {
    private void setProductActucalPrice(Product product){
        product.setActual_price(product.getPrice().subtract(product.getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(product.getPrice())));
    }

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product save(Product product) {
        setProductActucalPrice(product);

        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public boolean existsByName(String name) {

        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Product t where t.name='" + name +"'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query).setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public void deleteProductById(Long id) {
    Query query=sessionFactory.getCurrentSession().createQuery("delete from Product where id="+id);

    query.executeUpdate();
    }

    @Override
    public List findAllProductsByCategory(Category category) {
        return sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("category", category)).list();
    }

    @Override
    public List<Product> findAllProducts() {

        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }

    @Override
    public void changeProductInformation(Long id, Product product) {
       setProductActucalPrice(product);
sessionFactory.getCurrentSession().update(product);





    }
}
