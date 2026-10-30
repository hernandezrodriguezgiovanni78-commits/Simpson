/**
 * Clase que implementa el método de Integración Numérica por Trapecio Compuesto
 * con un criterio de paro basado en el error absoluto y duplicación de subintervalos.
 */
public class TrapecioCompuesto {

    /**
     * Define la función matemática f(x) que se desea integrar.
     * @param x Valor de la variable independiente.
     * @return El valor evaluado de la función f(x).
     */
    public static double funcion(double x) {
        return Math.pow(x, 2); // Ejemplo: f(x) = x^2
    }

    /**
     * Calcula el tamaño del paso h.
     * Se declara public static para que la clase SimpsonCompuesto pueda reutilizarlo directamente.
     * @param a Límite inferior.
     * @param b Límite superior.
     * @param n Número de subintervalos.
     * @return El ancho de cada rebanada h.
     */
    public static double calcularH(double a, double b, int n) {
        return (b - a) / n;
    }

    /**
     * Método base que calcula la aproximación de la integral mediante la
     * regla del Trapecio Compuesta para un número fijo de subintervalos (n).
     * Fórmula: I = (h/2) * [ f(a) + 2*sum(f(x_k)) + f(b) ]
     */
    private static double calcularTrapecio(double a, double b, int n) {
        // Reutilizamos el método calcularH que definimos arriba
        double h = calcularH(a, b, n);
        double suma = funcion(a) + funcion(b); // Suma inicial con los extremos

        // Ciclo para sumar los puntos interiores multiplicados por 2
        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            suma += 2 * funcion(xk);
        }

        return (h / 2) * suma;
    }

    /**
     * Método iterativo que aplica el criterio de paro por error absoluto.
     * Duplica el número de subintervalos (n -> 2n) en cada iteración del ciclo while
     * hasta que la diferencia entre dos aproximaciones sucesivas sea menor a la tolerancia.
     */
    public static double integrarConTolerancia(double a, double b, double tolerancia) {
        int n = 2; // Número inicial de subintervalos
        double integralAnterior = calcularTrapecio(a, b, n);
        double errorAbsoluto = 999.0; // Inicializamos con un valor grande para entrar al while

        System.out.println("--- ITERACIONES (TRAPECIO COMPUESTO) ---");
        System.out.println("n = " + n + " | Integral = " + integralAnterior);

        // Ciclo while para evaluar la convergencia iterativa
        while (errorAbsoluto > tolerancia) {
            n *= 2; // Duplicamos los subintervalos (el paso h se reduce a la mitad)
            double integralNueva = calcularTrapecio(a, b, n);

            // Cálculo del error absoluto entre la iteración nueva y la anterior
            errorAbsoluto = Math.abs(integralNueva - integralAnterior);

            System.out.println("n = " + n + " | Integral = " + integralNueva + " | Error = " + errorAbsoluto);

            // Actualizamos el valor anterior para la siguiente comparación
            integralAnterior = integralNueva;
        }

        return integralAnterior;
    }
}
