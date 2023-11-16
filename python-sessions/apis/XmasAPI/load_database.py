import sqlite3

# Datos de Gifts
gifts_data = [
    {"mensaje": "Mensaje 1", "regalos": "Regalo1, Regalo2, Regalo3"},
    {"mensaje": "Mensaje 2", "regalos": "Regalo4, Regalo5"},
    # Agrega más datos según sea necesario
]

# Conecta con la base de datos SQLite3
conn = sqlite3.connect("db.sqlite3")
cursor = conn.cursor()

# Inserta los datos en la tabla Gifts
for data in gifts_data:
    cursor.execute("INSERT INTO xmasrest04app_gifts (mensaje, regalos) VALUES (?, ?)",
                   (data["mensaje"], data["regalos"]))

# Guarda los cambios
conn.commit()

# Cierra la conexión
conn.close()

print("Datos de Gifts insertados con éxito.")
