import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findBySentimento(Sentimento sentimento);
}