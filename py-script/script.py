import psycopg2
from faker import Faker
from random import randrange
from datetime import datetime, timedelta, date

con = psycopg2.connect(database="ems", user="postgres", password="root1234", host="localhost", port="5432")
cur = con.cursor()
fake = Faker()

FIX_NUM = 12345

for i in range(5000000):
    print("Processed: ", i)
    name = fake.name().split(' ')
    firstName, lastName = name[0:2]
    employeeCode = FIX_NUM + i
    designation = randrange(0, 4)
    dateOfJoining = date.today() + timedelta(days=randrange(-2000, -365))
    try:
        cur.execute("""
                INSERT INTO employees (first_name, last_name, employee_code, designation, date_of_joining, created_at, updated_at)
                VALUES (%s, %s, %s, %s, %s, %s, %s)""", (firstName, lastName, employeeCode, designation, dateOfJoining,
                                                         datetime.now(), datetime.now(),))
        if i % 50000 == 0:
            con.commit()
    except Exception as e:
        print("Something went wrong :: ", e)

con.commit()
con.close()
