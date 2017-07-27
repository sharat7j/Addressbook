package com.addressbook.repository;

/**
 * Created by sharatjagannath on 7/24/17.
 */
import com.addressbook.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    @Query(value = "select c from Contact c where c.phoneNumber = :phoneNumber")
    Contact phoneExists(@Param("phoneNumber") String name);

    @Query(value = "select c from Contact c where c.state = :state")
    Page<Contact> contactsByState(@Param("state") String name, Pageable page);


    @Modifying
    @Transactional
    @Query(value = "delete from Contact u where u.id = :id")
    void deleteContact(@Param("id") Long id);
}