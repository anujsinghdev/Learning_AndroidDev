import sqlite3

def create_table():

    conn = sqlite3.connect('my_medicalshop.db')
    cursor = conn.cursor()

    cursor.execute('''
        CREATE TABLE IF NOT EXISTS Users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id VARCHAR(50) ,
                password VARCHAR(50),
                date_of_account_created DATE,
                phoneNumber TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                pinCode TEXT NOT NULL,
                address TEXT NOT NULL
                
    )
             
''' )
    
    conn.commit()
    conn.close()
                   

