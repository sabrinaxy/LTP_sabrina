import org.springframework.data.jpa.repository.JpaRepository;

public interface SentimentoRepository extends JpaRepository<Sentimento, Long> {
    List<Sentimento> findByNome(String nome);
}