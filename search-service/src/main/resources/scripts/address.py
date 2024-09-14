import random
from datetime import datetime, timedelta

# Listas de exemplo para gerar dados aleatórios
streets = ["Rua", "Avenida", "Alameda", "Travessa", "Praça"]
street_names = ["das Flores", "dos Ipês", "das Palmeiras", "dos Girassóis", "das Acácias", "dos Pinheiros",
                "das Orquídeas", "dos Cravos", "das Rosas", "dos Jasmins"]
cities = ["São Paulo", "Rio de Janeiro", "Belo Horizonte", "Salvador", "Fortaleza", "Brasília", "Curitiba",
          "Recife", "Porto Alegre", "Manaus", "Belém", "Goiânia", "Guarulhos", "Campinas", "São Luís"]
states = ["SP", "RJ", "MG", "BA", "CE", "DF", "PR", "PE", "RS", "AM", "PA", "GO", "SP", "SP", "MA"]

def generate_address():
    street = f"{random.choice(streets)} {random.choice(street_names)}, {random.randint(1, 2000)}"
    city_index = random.randint(0, len(cities) - 1)
    city = cities[city_index]
    state = states[city_index]
    zipcode = f"{random.randint(10000, 99999)}{random.randint(100, 999)}"
    latitude = random.uniform(-33.75, 5.27)  # Latitude range for Brazil
    longitude = random.uniform(-73.99, -34.79)  # Longitude range for Brazil
    date = datetime.now() - timedelta(days=random.randint(0, 365))
    return (street, city, state, zipcode, latitude, longitude, date.strftime("%Y-%m-%d %H:%M:%S"))

# Gerar SQL
sql = "INSERT INTO public.addresses (id, street, city, state, zipcode, country, latitude, longitude, date_created, last_updated) VALUES\n"

for i in range(1, 501):  # Gerar 500 registros
    address = generate_address()
    sql += f"({i}, '{address[0]}', '{address[1]}', '{address[2]}', '{address[3]}', 'Brasil', {address[4]:.4f}, {address[5]:.4f}, '{address[6]}', '{address[6]}'),\n"

sql = sql.rstrip(",\n") + ";"

# Salvar o SQL em um arquivo
with open("populate_addresses.sql", "w", encoding="utf-8") as f:
    f.write(sql)

print("SQL gerado e salvo em 'populate_addresses.sql'")