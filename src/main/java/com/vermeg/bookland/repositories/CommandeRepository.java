package com.vermeg.bookland.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.bookland.dtos.BookVenteStatDto;
import com.vermeg.bookland.dtos.ChiffreAffaireBookDto;
import com.vermeg.bookland.dtos.FactureDto;
import com.vermeg.bookland.entities.Commande;





@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long>{
	
	@Query(value="SELECT b.title as title,b.price as price ,b.picture as picture ,l.commande_date as commande from book b, commande c, ligne_commande l where c.id=l.commande_id and l.book_id=b.id and c.id_user=?1 ",nativeQuery = true)
	List<FactureDto> findFacture(long id);
	@Query(value="SELECT b.title as title,b.price as price ,b.picture as picture ,l.commande_date as commande from book b, commande c, ligne_commande l where c.id=l.commande_id and l.book_id=b.id and c.id_user=?1 and l.commande_date=?2",nativeQuery = true)
	List<FactureDto> findFacturepdf(long id,String d);
	@Query(value="select b.title, sum(l.qte) as totalVenteBook from ligne_commande l , book b where b.id=l.book_id GROUP by l.book_id",nativeQuery = true)
	List<BookVenteStatDto> findTotalVenteBybook();
	@Query(value="select sum(book.price*ligne_commande.qte) as total, DATE_FORMAT(ligne_commande.commande_date,'%b %Y') as date from book ,ligne_commande  where ligne_commande.book_id=book.id and DATE_FORMAT(ligne_commande.commande_date,'%Y') = YEAR(CURRENT_DATE) GROUP by date ;",nativeQuery = true)
	List<ChiffreAffaireBookDto> findChiffreAffaireYear();
}
