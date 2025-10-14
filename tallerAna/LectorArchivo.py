import os

class LectorArchivo:
    
    @staticmethod
    def generar_arreglo_desde_archivo(tamaño):
        current_dir = os.path.dirname(__file__)  # carpeta donde está LectorArchivo.py
        ruta_archivo = os.path.join(current_dir, "numeros_aleatorios.txt")
        arreglo = [0] * tamaño
        contador = 0
        
        try:
            with open(ruta_archivo, 'r', encoding='utf-8') as archivo:
                for linea in archivo:
                    if contador >= tamaño:
                        break
                    
                    linea_limpia = linea.strip()
                    if linea_limpia:  # Verificar que la línea no esté vacía
                        try:
                            arreglo[contador] = int(linea_limpia)
                            contador += 1
                        except ValueError:
                            print(f"Error: línea no es un número válido: {linea_limpia}")
            
            if contador < tamaño:
                print(f"Advertencia: El archivo solo contiene {contador} números, pero se solicitó {tamaño}")
                # Retornar solo los elementos leídos
                return arreglo[:contador]
                
        except FileNotFoundError:
            print(f"Error: No se pudo encontrar el archivo {ruta_archivo}")
            return []
        except IOError as e:
            print(f"Error al leer el archivo: {e}")
            return []
        
        return arreglo

    @staticmethod
    def verificar_archivo():
        """
        Verifica si el archivo existe y muestra información básica
        """
        ruta_archivo = "numeros_aleatorios.txt"
        
        if not os.path.exists(ruta_archivo):
            print(f"El archivo {ruta_archivo} no existe")
            return False
        
        # Contar líneas del archivo
        try:
            with open(ruta_archivo, 'r', encoding='utf-8') as archivo:
                lineas_totales = sum(1 for _ in archivo)
            print(f"El archivo {ruta_archivo} existe y tiene {lineas_totales} líneas")
            return True
        except IOError as e:
            print(f"Error al verificar el archivo: {e}")
            return False

# Función independiente para facilitar el uso
def generar_arreglo(tamaño):
    """Función independiente que usa la clase LectorArchivo"""
    return LectorArchivo.generar_arreglo_desde_archivo(tamaño)

