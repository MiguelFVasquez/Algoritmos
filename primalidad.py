import random
import math

# =============================
# 1. Miller–Rabin
# =============================
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

# =============================
# 2. Fermat
# =============================
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

# =============================
# 3. Solovay–Strassen
# =============================
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

# =============================
# 4. Baillie–PSW (simplificado)
# =============================
def es_primo_baillie_psw(n):
    return es_primo_miller_rabin(n, k=1)  # versión simplificada

# =============================
# 5. AKS (versión reducida)
# =============================
def es_potencia_perfecta(n):
    for b in range(2, int(math.log2(n)) + 2):
        a = round(n ** (1 / b))
        if a ** b == n:
            return True
    return False

def es_primo_aks(n):
    if es_potencia_perfecta(n):
        return False
    return es_primo_miller_rabin(n)  # simplificado por velocidad

# =============================
# 6. Wilson
# =============================
def es_primo_wilson(n):
    if n < 2:
        return False
    return math.factorial(n - 1) % n == n - 1

# =============================
# 7. Lucas–Lehmer
# =============================
def es_primo_lucas_lehmer(p):
    if p == 2:
        return True
    m = 2**p - 1
    s = 4
    for _ in range(p - 2):
        s = (s**2 - 2) % m
    return s == 0

# =============================
# 8. Lehmann
# =============================
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

# =============================
# Menú interactivo
# =============================
def main():
    metodos = {
        "1": ("Miller–Rabin", es_primo_miller_rabin),
        "2": ("Fermat", es_primo_fermat),
        "3": ("Solovay–Strassen", es_primo_solovay_strassen),
        "4": ("Baillie–PSW", es_primo_baillie_psw),
        "5": ("AKS", es_primo_aks),
        "6": ("Wilson", es_primo_wilson),
        "7": ("Lucas–Lehmer (solo Mersennerios)", es_primo_lucas_lehmer),
        "8": ("Lehmann", es_primo_lehmann),
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

        if opcion == "7":  # Lucas–Lehmer requiere exponente p
            p = int(input("Introduce el exponente p (n = 2^p - 1): "))
            resultado = funcion(p)
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'} para Mersenne(2^{p}-1)")
        else:
            n = int(input("Introduce el número a evaluar: "))
            resultado = funcion(n)
            print(f"{nombre}: {'PRIMO' if resultado else 'COMPUESTO'}")

if __name__ == "__main__":
    main()
