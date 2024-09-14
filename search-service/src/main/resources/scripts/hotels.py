import random
import uuid
from datetime import datetime, timedelta

# Assumindo que você já tenha uma lista de address_ids válidos
# Se não tiver, você precisará gerar ou obter esses IDs de alguma forma
address_ids = list(range(1, 501))  # Exemplo: 500 endereços já existentes

# Lista de nomes de hotéis fictícios
hotel_names = [
    "Grand Hotel", "Sunset Resort", "City View Inn", "Ocean Breeze Hotel", "Mountain Lodge",
    "Riverside Retreat", "Palm Garden Hotel", "Skyline Plaza", "Harmony Suites", "Royal Palace Hotel",
    "Green Valley Resort", "Blue Lagoon Inn", "Golden Sands Hotel", "Silver Moon Lodge", "Crystal Springs Resort",
    "Emerald Bay Hotel", "Ruby Hills Inn", "Sapphire Shores Resort", "Diamond Peak Lodge", "Oasis Palms Hotel"
]

# Função para gerar um registro de hotel
def generate_hotel(id):
    name = f"{random.choice(hotel_names)} {random.choice(['', 'Express', 'Plus', 'Deluxe', 'Premium'])}"
    rating = round(random.uniform(3.0, 5.0), 1)
    external_id = uuid.uuid4()
    address_id = random.choice(address_ids)
    address_ids.remove(address_id)  # Garante que cada endereço é usado apenas uma vez
    date_created = datetime.now() - timedelta(days=random.randint(0, 365))
    last_updated = date_created + timedelta(days=random.randint(0, 30))
    
    return (id, name, rating, external_id, address_id, date_created, last_updated)

# Gerar 500 hotéis
hotels = [generate_hotel(10000 + i) for i in range(500)]

# Gerar o SQL
sql = "INSERT INTO hotels (id, name, rating, external_id, address_id, date_created, last_updated) VALUES\n"
sql += ",\n".join([f"({id}, '{name}', {rating}, '{external_id}', {address_id}, '{date_created}', '{last_updated}')" 
                   for id, name, rating, external_id, address_id, date_created, last_updated in hotels])
sql += ";"

# Imprimir o SQL
print(sql)

# Opcionalmente, você pode salvar o SQL em um arquivo
with open('populate_hotels.sql', 'w') as f:
    f.write(sql)