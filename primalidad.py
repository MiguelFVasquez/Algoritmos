import random   # Librería para generar números aleatorios (usada en las pruebas probabilísticas)
import math     # Funciones matemáticas como factorial o raíz cuadrada
import time     # Para medir tiempos de ejecución

# ======================================================
# 1. PRUEBA DE MILLER–RABIN
# ======================================================
def es_primo_miller_rabin(n, k=5):
    if n < 2:
        return False
    if n in (2, 3):
        return True
    if n % 2 == 0:
        return False
    r, d = 0, n - 1
    while d % 2 == 0:
        r += 1
        d //= 2
    for _ in range(k):
        a = random.randint(2, n - 2)
        x = pow(a, d, n)
        if x in (1, n - 1):
            continue
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                break
        else:
            return False
    return True


# ======================================================
# 2. PRUEBA DE FERMAT
# ======================================================
def es_primo_fermat(n, k=5):
    if n < 2:
        return False
    if n == 2:
        return True
    for _ in range(k):
        a = random.randint(2, n - 2)
        if pow(a, n - 1, n) != 1:
            return False
    return True


# ======================================================
# 3. PRUEBA DE SOLOVAY–STRASSEN
# ======================================================
def jacobi(a, n):
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
        x = jacobi(a, n)
        if x == 0 or pow(a, (n - 1) // 2, n) != x % n:
            return False
    return True


# ======================================================
# 4. PRUEBA DE BAILLIE–PSW (simplificada)
# ======================================================
def es_primo_baillie_psw(n):
    return es_primo_miller_rabin(n, k=1)


# ======================================================
# 5. PRUEBA DE AKS (simplificada)
# ======================================================
def es_potencia_perfecta(n):
    for b in range(2, int(math.log2(n)) + 2):
        a = round(n ** (1 / b))
        if a ** b == n:
            return True
    return False

def es_primo_aks(n):
    if es_potencia_perfecta(n):
        return False
    return es_primo_miller_rabin(n)


# ======================================================
# 6. PRUEBA DE WILSON
# ======================================================
def es_primo_wilson(n):
    if n < 2:
        return False
    return math.factorial(n - 1) % n == n - 1


# ======================================================
# 7. PRUEBA DE LUCAS–LEHMER
# ======================================================
def es_primo_lucas_lehmer(p):
    if p == 2:
        return True
    m = 2**p - 1
    s = 4
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
        if r not in (1, n - 1):
            return False
    return True


# ======================================================
# 9–13. ALGORITMOS BÁSICOS CONVERTIDOS DE JAVA
# ======================================================
def primo_basico1(numero):
    """Versión directa: prueba todos los divisores"""
    resultado = 0
    for i in range(2, numero):
        if numero % i == 0:
            resultado = 1
    return resultado == 0

def primo_basico2(numero):
    """Optimización: prueba hasta numero/2"""
    centi = True
    i = 2
    while i <= numero / 2 and centi:
        if numero % i == 0:
            centi = False
        i += 1
    return centi

def primo_basico3(numero):
    """Versión con break y verificación final"""
    i = 2
    while i <= numero / 2:
        if numero % i == 0:
            break
        i += 1
    return numero / 2 < i

def primo_basico4(numero):
    """Usa raíz cuadrada como límite"""
    for i in range(2, int(math.sqrt(numero)) + 1):
        if numero % i == 0:
            return False
    return True

def primo_basico5(numero):
    """Optimización: usa i*i <= numero"""
    if numero < 2:
        return False
    i = 2
    while i * i <= numero:
        if numero % i == 0:
            return False
        i += 1
    return True


# ======================================================
# MENÚ INTERACTIVO
# ======================================================
def main():
    metodos = {
        "1": ("Miller–Rabin", es_primo_miller_rabin),
        "2": ("Fermat", es_primo_fermat),
        "3": ("Solovay–Strassen", es_primo_solovay_strassen),
        "4": ("Baillie–PSW", es_primo_baillie_psw),
        "5": ("AKS", es_primo_aks),
        "6": ("Wilson", es_primo_wilson),
        "7": ("Lucas–Lehmer (Mersenne)", es_primo_lucas_lehmer),
        "8": ("Lehmann", es_primo_lehmann),
        "9": ("Básico 1", primo_basico1),
        "10": ("Básico 2", primo_basico2),
        "11": ("Básico 3", primo_basico3),
        "12": ("Básico 4", primo_basico4),
        "13": ("Básico 5", primo_basico5),
    }

    while True:
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

        if opcion == "7":
            p = int(input("Introduce el exponente p (n = 2^p - 1): "))
            inicio = time.perf_counter()
            resultado = funcion(p)
            fin = time.perf_counter()
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'}")
            print(f"Tiempo: {fin - inicio:.6f} segundos")
        else:
            n = int(input("Introduce el número a evaluar: "))
            inicio = time.perf_counter()
            resultado = funcion(n)
            fin = time.perf_counter()
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'}")
            print(f"Tiempo: {fin - inicio:.6f} segundos")


if __name__ == "__main__":
    main()
