import random
import uuid
from datetime import datetime, timedelta

# Lista de IDs de hotéis válidos
hotel_ids = list(range(10000, 10500))  # IDs de 10000 a 10499


# Função para gerar um registro de review
def generate_review(id, hotel_id):
    id_external_user = uuid.uuid4()
    rating = round(random.uniform(0, 5), 1)  # Rating de 0 a 5
    review_text = f"Review for hotel {hotel_id}."
    date_created = datetime.now() - timedelta(days=random.randint(1, 365))  # Data aleatória nos últimos 365 dias
    last_updated = datetime.now()

    return (id, id_external_user, rating, review_text, hotel_id, date_created, last_updated)


# Gerar reviews para cada hotel (5 a 10 reviews por hotel)
reviews = []
review_id = 10000  # Começando com o ID 10000 conforme a sequência definida

for hotel_id in hotel_ids:
    num_reviews = random.randint(5, 10)  # Número aleatório de reviews entre 5 e 10
    for _ in range(num_reviews):
        reviews.append(generate_review(review_id, hotel_id))
        review_id += 1

# Gerar o SQL
sql = "INSERT INTO user_reviews (id, id_external_user, rating, review, hotel_id, date_created, last_updated) VALUES\n"
sql += ",\n".join([
    f"({id}, '{id_external_user}', {rating}, '{review_text}', {hotel_id}, '{date_created}', '{last_updated}')"
    for id, id_external_user, rating, review_text, hotel_id, date_created, last_updated in reviews
])
sql += ";"

# Imprimir o SQL
print(sql)

# Opcionalmente, você pode salvar o SQL em um arquivo
with open('populate_user_reviews.sql', 'w') as f:
    f.write(sql)

# Imprimir algumas estatísticas
print(f"\nTotal de reviews gerados: {len(reviews)}")
print(f"Número médio de reviews por hotel: {len(reviews) / len(hotel_ids):.2f}")
