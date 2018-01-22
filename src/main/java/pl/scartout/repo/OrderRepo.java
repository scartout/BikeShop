package pl.scartout.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Order;
import pl.scartout.model.User;

@Transactional
@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	
	List<Order> findByUser(User user);

	List<Order> findByUserId(User user);
	
	@Modifying
	@Query("UPDATE Order c SET c.orderDate=:orderDate, c.quantity=:quantity, c.total=:total WHERE c.id = :id")
    int confirmOrder(@Param("id") Long id, @Param("orderDate") Date orderDate, @Param("quantity") int quantity, 
    		@Param("total") double total);
	
	@Modifying
	@Query("UPDATE Order c SET c.approvalDate=:approvalDate WHERE c.id = :id")
    int confirmApproval(@Param("id") Long id, @Param("approvalDate") Date approval_date);
	
	@Modifying
	@Query("UPDATE Order c SET c.shippingDate=:shippingDate WHERE c.id = :id")
    int confirmShipping(@Param("id") Long id, @Param("shippingDate") Date shippingDate);
	
	@Modifying
	@Query("UPDATE Order c SET c.completeDate=:completeDate WHERE c.id = :id")
    int confirmComplete(@Param("id") Long id, @Param("completeDate") Date completeDate);
	
	@Modifying(clearAutomatically = true)
    @Query("DELETE FROM Order c WHERE c.id = :id")
    int deleteOrder(@Param("id") long id);

	@Query("SELECT c FROM Order c WHERE c.user=:user AND c.orderDate is null")
	List<Order> findByUserAndOrderDateIsNull(@Param("user") User user);
	
	@Query("SELECT c FROM Order c WHERE c.orderDate is not null")
	List<Order> findByOrderDateIsNotNull();
	
	@Query("SELECT c FROM Order c WHERE c.user=:user AND c.orderDate is not null")
	List<Order> findByUserAndOrderDateIsNotNull(@Param("user") User user);
	
	@Query("SELECT count(c) FROM Order c WHERE c.user=:user AND c.orderDate is null")
	int countOrdersByUserId(@Param("user") User user);

}