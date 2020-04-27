package Context.aware.programming.Ipl.Entity;

@Entity
public class Camara {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
}
