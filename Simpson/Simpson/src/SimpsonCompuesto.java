/**
 * Clase que implementa el método de Integración Numérica por Simpson 1/3 Compuesto,
 * reutilizando la función f(x) y el cálculo del paso h directamente de la clase TrapecioCompuesto
 */
public class SimpsonCompuesto {

    /**
     * Método base que calcula la aproximación de la integral mediante la
     * regla de Simpson 1/3 Compuesta para un número par fijo de subintervalos (n).
     * Fórmula: I = (h/3) * [ f(x_0) + 4*sum(f_impares) + 2*sum(f_pares) + f(x_n) ]
     *
     * @param a Límite inferior del intervalo.
     * @param b Límite superior del intervalo.
     * @param n Número de subintervalos (debe ser par).
     * @return La aproximación numérica de la integral para ese n.
     */
    private static double calcularSimpson(double a, double b, int n) {
        // Reutilizamos el método calcularH de la clase TrapecioCompuesto
        double h = TrapecioCompuesto.calcularH(a, b, n);
        double sumaImpares = 0.0;
        double sumaPares = 0.0;

        // Recorremos los puntos intermedios desde k = 1 hasta n-1
        for (int k = 1; k < n; k++) {
            double xk = a + k * h;

            // Si k is impar, lleva coeficiente 4; si k es par, lleva coeficiente 2
            if (k % 2 != 0) {
                // Reutilizamos la función f(x) de la clase TrapecioCompuesto
                sumaImpares += TrapecioCompuesto.funcion(xk);
            } else {
                sumaPares += TrapecioCompuesto.funcion(xk);
            }
        }

        // Aplicación de la fórmula general de Simpson Compuesto
        double sumaTotal = TrapecioCompuesto.funcion(a) + 4 * sumaImpares
                + 2 * sumaPares + TrapecioCompuesto.funcion(b);
        return (h / 3.0) * sumaTotal;
    }

    /**
     * Método iterativo que aplica el criterio de paro por error absoluto,
     * duplicando el número de subintervalos pares en cada iteración.
     */
    public static double integrarConTolerancia(double a, double b, double tolerancia) {
        int n = 2; // Número inicial de subintervalos (debe ser par para Simpson 1/3)
        double integralAnterior = calcularSimpson(a, b, n);
        double errorAbsoluto = 999.0;

        System.out.println("\n--- ITERACIONES (SIMPSON 1/3 COMPUESTO) ---");
        System.out.println("n = " + n + " | Integral = " + integralAnterior);

        while (errorAbsoluto > tolerancia) {
            n *= 2; // Duplicamos los subintervalos manteniendo la paridad
            double integralNueva = calcularSimpson(a, b, n);
            errorAbsoluto = Math.abs(integralNueva - integralAnterior);

            System.out.println("n = " + n + " | Integral = " + integralNueva + " | Error = " + errorAbsoluto);

            integralAnterior = integralNueva;
        }

        return integralAnterior;
    }
}