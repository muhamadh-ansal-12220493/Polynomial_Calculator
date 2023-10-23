import java.util.LinkedList;
import java.util.Scanner;

class PolynomialTerm {
    int coefficient;
    int exponent;

    public PolynomialTerm(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public String toString() {
        return coefficient + "x^" + exponent;
    }
}

public class PolynomialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList<PolynomialTerm> poly1 = new LinkedList<>();
        LinkedList<PolynomialTerm> poly2 = new LinkedList<>();

        System.out.println("Enter the first polynomial:");
        inputPolynomial(scanner, poly1);

        System.out.println("Enter the second polynomial:");
        inputPolynomial(scanner, poly2);

        System.out.print("Polynomial 1: ");
        displayPolynomial(poly1);

        System.out.print("Polynomial 2: ");
        displayPolynomial(poly2);

        LinkedList<PolynomialTerm> additionResult = addPolynomials(poly1, poly2);
        System.out.print("Addition Result: ");
        displayPolynomial(additionResult);

        LinkedList<PolynomialTerm> subtractionResult = subtractPolynomials(poly1, poly2);
        System.out.print("Subtraction Result: ");
        displayPolynomial(subtractionResult);

        LinkedList<PolynomialTerm> multiplicationResult = multiplyPolynomials(poly1, poly2);
        System.out.print("Multiplication Result: ");
        displayPolynomial(multiplicationResult);

        scanner.close();
    }

    private static void inputPolynomial(Scanner scanner, LinkedList<PolynomialTerm> polynomial) {
        System.out.print("Enter the number of terms: ");
        int numTerms = scanner.nextInt();

        for (int i = 0; i < numTerms; i++) {
            System.out.print("Enter coefficient and exponent (e.g., 2 3): ");
            int coefficient = scanner.nextInt();
            int exponent = scanner.nextInt();

            if (coefficient != 0) {
                polynomial.add(new PolynomialTerm(coefficient, exponent));
            }
        }
    }

    private static void displayPolynomial(LinkedList<PolynomialTerm> polynomial) {
        for (PolynomialTerm term : polynomial) {
            System.out.print(term + " + ");
        }
        System.out.println("0");
    }

    private static LinkedList<PolynomialTerm> addPolynomials(
            LinkedList<PolynomialTerm> poly1, LinkedList<PolynomialTerm> poly2) {
        LinkedList<PolynomialTerm> result = new LinkedList<>();
        result.addAll(poly1);
        for (PolynomialTerm term2 : poly2) {
            boolean found = false;
            for (PolynomialTerm term1 : result) {
                if (term1.exponent == term2.exponent) {
                    term1.coefficient += term2.coefficient;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(new PolynomialTerm(term2.coefficient, term2.exponent));
            }
        }
        return result;
    }

    private static LinkedList<PolynomialTerm> subtractPolynomials(
            LinkedList<PolynomialTerm> poly1, LinkedList<PolynomialTerm> poly2) {
        LinkedList<PolynomialTerm> result = new LinkedList<>();
        result.addAll(poly1);
        for (PolynomialTerm term2 : poly2) {
            boolean found = false;
            for (PolynomialTerm term1 : result) {
                if (term1.exponent == term2.exponent) {
                    term1.coefficient -= term2.coefficient;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(new PolynomialTerm(-term2.coefficient, term2.exponent));
            }
        }
        return result;
    }

    private static LinkedList<PolynomialTerm> multiplyPolynomials(
            LinkedList<PolynomialTerm> poly1, LinkedList<PolynomialTerm> poly2) {
        LinkedList<PolynomialTerm> result = new LinkedList<>();

        for (PolynomialTerm term1 : poly1) {
            for (PolynomialTerm term2 : poly2) {
                int newCoefficient = term1.coefficient * term2.coefficient;
                int newExponent = term1.exponent + term2.exponent;

                boolean found = false;
                for (PolynomialTerm term : result) {
                    if (term.exponent == newExponent) {
                        term.coefficient += newCoefficient;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    result.add(new PolynomialTerm(newCoefficient, newExponent));
                }
            }
        }

        return result;
    }
}
