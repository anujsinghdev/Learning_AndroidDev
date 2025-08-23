from datetime import date
import sqlite3
import logging
from typing import Dict, List, Optional, Any

class DatabaseError(Exception):
    """Custom exception for database operations"""
    pass

def _get_connection():
    """Get database connection with error handling"""
    try:
        return sqlite3.connect('my_medicalshop.db')
    except sqlite3.Error as e:
        logging.error(f"Database connection error: {e}")
        raise DatabaseError(f"Failed to connect to database: {e}")

def createUser(name: str, password: str, phoneNumber: str, email: str, pinCode: str, address: str) -> str:
    """Insert a new user into the Users table."""
    if not all([name, password, phoneNumber, email, pinCode, address]):
        raise ValueError("All fields are required")
    
    conn = None
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        userid = str(name) + str(phoneNumber)[-4:]
        date_of_account_created = date.today().isoformat()
        
        cursor.execute(
            '''
            INSERT INTO Users (user_id, password, date_of_account_created, phoneNumber, email, pinCode, address)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            ''',
            (userid, password, date_of_account_created, phoneNumber, email, pinCode, address)
        )
        
        conn.commit()
        return userid
        
    except sqlite3.IntegrityError as e:
        logging.error(f"User creation failed - integrity error: {e}")
        raise DatabaseError(f"User creation failed: {e}")
    except sqlite3.Error as e:
        logging.error(f"Database error during user creation: {e}")
        raise DatabaseError(f"Database error: {e}")
    finally:
        if conn:
            conn.close()

def get_all_users() -> List[Dict[str, Any]]:
    """Return a list of all users as dicts."""
    conn = None
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        cursor.execute(
            "SELECT id, user_id, password, date_of_account_created, phoneNumber, email, pinCode, address FROM Users"
        )
        rows = cursor.fetchall()
        
        columns = [
            "id", "user_id", "password", "date_of_account_created", 
            "phoneNumber", "email", "pinCode", "address"
        ]
        return [dict(zip(columns, row)) for row in rows]
        
    except sqlite3.Error as e:
        logging.error(f"Error fetching all users: {e}")
        raise DatabaseError(f"Failed to fetch users: {e}")
    finally:
        if conn:
            conn.close()

def get_user_by_id(user_id: int) -> Optional[Dict[str, Any]]:
    """Get user by ID. Returns None if user not found."""
    conn = None
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        cursor.execute(
            "SELECT id, user_id, password, date_of_account_created, phoneNumber, email, pinCode, address FROM Users WHERE id = ?",
            (user_id,)
        )
        row = cursor.fetchone()
        
        if not row:
            return None
            
        columns = [
            "id", "user_id", "password", "date_of_account_created",
            "phoneNumber", "email", "pinCode", "address"
        ]
        return dict(zip(columns, row))
        
    except sqlite3.Error as e:
        logging.error(f"Error fetching user {user_id}: {e}")
        raise DatabaseError(f"Failed to fetch user: {e}")
    finally:
        if conn:
            conn.close()

def update_user(user_id: int, **fields) -> int:
    """Update user fields. Returns number of rows updated."""
    if not fields:
        return 0
        
    allowed_fields = {
        "user_id", "password", "date_of_account_created", 
        "phoneNumber", "email", "pinCode", "address"
    }
    
    updates = []
    params = []
    
    for key, value in fields.items():
        if key in allowed_fields:
            updates.append(f"{key} = ?")
            params.append(value)
    
    if not updates:
        return 0
    
    params.append(user_id)
    conn = None
    
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        query = f"UPDATE Users SET {', '.join(updates)} WHERE id = ?"
        cursor.execute(query, tuple(params))
        conn.commit()
        
        return cursor.rowcount
        
    except sqlite3.Error as e:
        logging.error(f"Error updating user {user_id}: {e}")
        raise DatabaseError(f"Failed to update user: {e}")
    finally:
        if conn:
            conn.close()

def delete_user(user_id: int) -> int:
    """Delete user by ID. Returns number of rows deleted."""
    conn = None
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        cursor.execute("DELETE FROM Users WHERE id = ?", (user_id,))
        conn.commit()
        
        return cursor.rowcount
        
    except sqlite3.Error as e:
        logging.error(f"Error deleting user {user_id}: {e}")
        raise DatabaseError(f"Failed to delete user: {e}")
    finally:
        if conn:
            conn.close()

# Additional utility functions
def user_exists(user_id: int) -> bool:
    """Check if user exists by ID."""
    return get_user_by_id(user_id) is not None

def get_user_by_phone(phone_number: str) -> Optional[Dict[str, Any]]:
    """Get user by phone number."""
    conn = None
    try:
        conn = _get_connection()
        cursor = conn.cursor()
        
        cursor.execute(
            "SELECT id, user_id, password, date_of_account_created, phoneNumber, email, pinCode, address FROM Users WHERE phoneNumber = ?",
            (phone_number,)
        )
        row = cursor.fetchone()
        
        if not row:
            return None
            
        columns = [
            "id", "user_id", "password", "date_of_account_created",
            "phoneNumber", "email", "pinCode", "address"
        ]
        return dict(zip(columns, row))
        
    except sqlite3.Error as e:
        logging.error(f"Error fetching user by phone {phone_number}: {e}")
        raise DatabaseError(f"Failed to fetch user by phone: {e}")
    finally:
        if conn:
            conn.close()
