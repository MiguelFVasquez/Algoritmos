import random   # Librería para generar números aleatorios (usada en las pruebas probabilísticas)
import math     # Librería con funciones matemáticas (factorial, logaritmos, etc.)

# ======================================================
# 1. PRUEBA DE MILLER–RABIN
# ======================================================
def es_primo_miller_rabin(n, k=5):
    """
    n: número entero a evaluar
    k: cantidad de iteraciones para reducir probabilidad de error
    """
    if n < 2:              # Si es menor que 2, no es primo
        return False
    if n in (2, 3):         # 2 y 3 son primos
        return True
    if n % 2 == 0:          # Si es par, no es primo
        return False

    # Escribimos n-1 como 2^r * d con d impar
    r, d = 0, n - 1
    while d % 2 == 0:       # Dividimos por 2 hasta que d sea impar
        r += 1
        d //= 2

    # Iteramos k veces para aumentar fiabilidad
    for _ in range(k):
        a = random.randint(2, n - 2)   # Elegimos base aleatoria a
        x = pow(a, d, n)               # Calculamos a^d mod n

        if x in (1, n - 1):            # Si es 1 o n-1, pasa a la siguiente ronda
            continue

        for _ in range(r - 1):         # Repetimos r-1 veces
            x = pow(x, 2, n)           # Elevamos al cuadrado mod n
            if x == n - 1:             # Si se convierte en n-1, pasa el test
                break
        else:                          # Si nunca llegó a n-1, es compuesto
            return False

    return True  # Probablemente primo


# ======================================================
# 2. PRUEBA DE FERMAT
# ======================================================
def es_primo_fermat(n, k=5):
    if n < 2:
        return False
    if n == 2:
        return True

    for _ in range(k):
        a = random.randint(2, n - 2)         # Base aleatoria
        if pow(a, n - 1, n) != 1:            # Si no cumple el teorema de Fermat
            return False                     # es compuesto
    return True


# ======================================================
# 3. PRUEBA DE SOLOVAY–STRASSEN
# ======================================================
def jacobi(a, n):
    """ Calcula el símbolo de Jacobi (a/n) """
    if n % 2 == 0:
        return 0
    result = 1
    a = a % n
    while a != 0:
        while a % 2 == 0:
            a //= 2
            if n % 8 in [3, 5]:
                result = -result
        a, n = n, a
        if a % 4 == n % 4 == 3:
            result = -result
        a %= n
    return result if n == 1 else 0

def es_primo_solovay_strassen(n, k=5):
    if n < 2:
        return False
    if n in (2, 3):
        return True
    if n % 2 == 0:
        return False
    for _ in range(k):
        a = random.randint(2, n - 2)
        x = jacobi(a, n)                     # Calcula símbolo de Jacobi
        if x == 0 or pow(a, (n - 1) // 2, n) != x % n:
            return False
    return True


# ======================================================
# 4. PRUEBA DE BAILLIE–PSW (simplificada)
# ======================================================
def es_primo_baillie_psw(n):
    # Aquí solo usamos Miller–Rabin base 2 como aproximación
    return es_primo_miller_rabin(n, k=1)


# ======================================================
# 5. PRUEBA DE AKS (versión reducida)
# ======================================================
def es_potencia_perfecta(n):
    """ Verifica si n es potencia perfecta (a^b) """
    for b in range(2, int(math.log2(n)) + 2):
        a = round(n ** (1 / b))
        if a ** b == n:
            return True
    return False

def es_primo_aks(n):
    if es_potencia_perfecta(n):  # Si es potencia perfecta, no es primo
        return False
    return es_primo_miller_rabin(n)  # Usamos MR por simplificación


# ======================================================
# 6. PRUEBA DE WILSON
# ======================================================
def es_primo_wilson(n):
    """
    Usa el teorema de Wilson:
    (n - 1)! ≡ -1 (mod n)  ⇔  n es primo
    """
    if n < 2:
        return False

    # math.factorial(n - 1) → calcula el factorial de n-1
    # % n → obtenemos el residuo de dividir el factorial entre n
    # n - 1 es equivalente a -1 mod n, condición para que sea primo
    return math.factorial(n - 1) % n == n - 1


# ======================================================
# 7. PRUEBA DE LUCAS–LEHMER (solo Mersenne)
# ======================================================
def es_primo_lucas_lehmer(p):
    """
    Prueba determinista para primos de Mersenne:
    n = 2^p - 1
    """
    if p == 2:
        return True
    m = 2**p - 1      # Número de Mersenne
    s = 4             # Valor inicial
    for _ in range(p - 2):
        s = (s**2 - 2) % m
    return s == 0


# ======================================================
# 8. PRUEBA DE LEHMANN
# ======================================================
def es_primo_lehmann(n, k=5):
    if n < 2:
        return False
    if n == 2:
        return True
    for _ in range(k):
        a = random.randint(2, n - 2)
        r = pow(a, (n - 1) // 2, n)
        if r not in (1, n - 1):  # Si no es ±1 mod n
            return False
    return True


# ======================================================
# MENÚ INTERACTIVO
# ======================================================
def main():
    # Diccionario con número de opción, nombre del método y función asociada
    metodos = {
        "1": ("Miller–Rabin", es_primo_miller_rabin),
        "2": ("Fermat", es_primo_fermat),
        "3": ("Solovay–Strassen", es_primo_solovay_strassen),
        "4": ("Baillie–PSW", es_primo_baillie_psw),
        "5": ("AKS", es_primo_aks),
        "6": ("Wilson", es_primo_wilson),
        "7": ("Lucas–Lehmer (solo Mersenne)", es_primo_lucas_lehmer),
        "8": ("Lehmann", es_primo_lehmann),
    }

    while True:
        # Mostrar menú
        print("\n=== Pruebas de Primalidad ===")
        for k, (nombre, _) in metodos.items():
            print(f"{k}. {nombre}")
        print("0. Salir")

        opcion = input("Elige un método: ")
        if opcion == "0":
            break
        if opcion not in metodos:
            print("Opción no válida.")
            continue

        nombre, funcion = metodos[opcion]

        # Lucas–Lehmer pide exponente p
        if opcion == "7":
            p = int(input("Introduce el exponente p (n = 2^p - 1): "))
            resultado = funcion(p)
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'} para Mersenne(2^{p}-1)")
        else:
            n = int(input("Introduce el número a evaluar: "))
            resultado = funcion(n)
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'}")

# Ejecutar programa si es archivo principal
if __name__ == "__main__":
    main()
