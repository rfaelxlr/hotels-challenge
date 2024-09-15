import random
import uuid
from datetime import datetime, timedelta

# Listas de exemplo para gerar dados aleatórios
names = ["João", "Maria", "Pedro", "Ana", "Lucas", "Carla", "Felipe", "Mariana", "Rafael", "Camila",
         "Gustavo", "Beatriz", "Bruno", "Isabela", "Thiago", "Juliana", "Gabriel", "Larissa", "Leonardo", "Fernanda"]
domains = ["gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "uol.com.br"]
ddds = ["11", "21", "31", "41", "51", "61", "71", "81", "91", "98"]


def generate_user():
    name = random.choice(names)
    email = f"{name.lower()}.{random.randint(1, 9999)}@{random.choice(domains)}"
    ddd = random.choice(ddds)
    phone_number = f"{random.randint(900000000, 999999999)}"
    external_id = uuid.uuid4()
    date = datetime.now() - timedelta(days=random.randint(0, 365))
    return (name, email, ddd, phone_number, external_id, date.strftime("%Y-%m-%d %H:%M:%S"))


# Gerar SQL
sql = "INSERT INTO public.users (id, name, email, ddd, phone_number, external_id, date_created, last_updated) VALUES\n"

for i in range(1, 501):  # Gerar 500 registros
    user = generate_user()
    sql += f"({i}, '{user[0]}', '{user[1]}', '{user[2]}', '{user[3]}', '{user[4]}', '{user[5]}', '{user[5]}'),\n"

sql = sql.rstrip(",\n") + ";"

# Salvar o SQL em um arquivo
with open("populate_users.sql", "w", encoding="utf-8") as f:
    f.write(sql)

print("SQL gerado e salvo em 'populate_users.sql'")
