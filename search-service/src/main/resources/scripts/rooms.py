import random
import uuid
from datetime import datetime, timedelta

# Assumindo que você já tenha uma lista de hotel_ids válidos
# Se não tiver, você precisará gerar ou obter esses IDs de alguma forma
hotel_ids = list(range(10000, 10500))  # Exemplo: 500 hotéis já existentes (IDs de 10000 a 10499)


# Função para gerar um registro de quarto
def generate_room(id, hotel_id):
    quantity_available = random.randint(1, 20)
    capacity = random.randint(1, 4)
    price = round(random.uniform(50, 500), 2)
    external_id = uuid.uuid4()
    date_created = datetime.now() - timedelta(days=random.randint(0, 365))
    last_updated = date_created + timedelta(days=random.randint(0, 30))

    return (id, quantity_available, capacity, price, external_id, hotel_id, date_created, last_updated)


# Gerar quartos para cada hotel (pelo menos 2 por hotel)
rooms = []
room_id = 10000  # Começando com o ID 10000 conforme a sequência definida

for hotel_id in hotel_ids:
    num_rooms = max(2, random.randint(2, 5))  # Garante pelo menos 2 quartos, com máximo de 5
    for _ in range(num_rooms):
        rooms.append(generate_room(room_id, hotel_id))
        room_id += 1

# Gerar o SQL
sql = "INSERT INTO rooms (id, quantity_available, capacity, price, external_id, hotel_id, date_created, last_updated) VALUES\n"
sql += ",\n".join([
                      f"({id}, {quantity_available}, {capacity}, {price:.2f}, '{external_id}', {hotel_id}, '{date_created}', '{last_updated}')"
                      for id, quantity_available, capacity, price, external_id, hotel_id, date_created, last_updated in
                      rooms])
sql += ";"

# Imprimir o SQL
print(sql)

# Opcionalmente, você pode salvar o SQL em um arquivo
with open('populate_rooms.sql', 'w') as f:
    f.write(sql)

# Imprimir algumas estatísticas
print(f"\nTotal de quartos gerados: {len(rooms)}")
print(f"Número médio de quartos por hotel: {len(rooms) / len(hotel_ids):.2f}")
