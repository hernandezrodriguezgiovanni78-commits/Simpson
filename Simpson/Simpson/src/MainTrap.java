public class MainTrap {
    public static void main(String[] args) {
        double a = 0.0;
        double b = 2.0;
        double tolerancia = 0.0001;

        // Mandamos a llamar al método encapsulado del Trapecio
        double resultadoTrapecio = TrapecioCompuesto.integrarConTolerancia(a, b, tolerancia);
        System.out.println("\nResultado final obtenido (Trapecio): " + resultadoTrapecio);

        System.out.println("\n--------------------------------------------------");

        // Mandamos a llamar al método encapsulado de Simpson 1/3
        double resultadoSimpson = SimpsonCompuesto.integrarConTolerancia(a, b, tolerancia);
        System.out.println("\nResultado final obtenido (Simpson 1/3): " + resultadoSimpson);
    }
}