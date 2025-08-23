from flask import Flask , jsonify , request
from addOperation import createUser, get_all_users, get_user_by_id, update_user, delete_user
from createTableOperation import create_table

app = Flask(__name__)

@app.route('/' , methods=['GET'])
def home():
    return jsonify ({ 'name' : 'Anuj', 'age' : 24 })

@app.route('/createUser' , methods=['POST'])
def create_user():

    try:
        name = request.form['name']
        password = request.form['password']
        phoneNumber = request.form['phoneNumber']
        email = request.form['email']
        pinCode = request.form['pinCode']
        address = request.form['address']

        createUser(name=name, password=password, phoneNumber=phoneNumber, email=email, pinCode=pinCode, address=address)

        return jsonify({'message': 'User created', "status": 201})
    except Exception as error:
        return jsonify({'message': str(error), "status": 500})


@app.route('/users', methods=['GET'])
def list_users():
    users = get_all_users()
    return jsonify(users)


@app.route('/users/<int:user_id>', methods=['GET'])
def get_user(user_id):
    user = get_user_by_id(user_id)
    if not user:
        return jsonify({'message': 'User not found'}), 404
    return jsonify(user)


@app.route('/users/<int:user_id>', methods=['PUT'])
def update_user_endpoint(user_id):
    data = request.get_json(force=True)
    updated = update_user(user_id, **data)
    if updated:
        return jsonify({'message': 'User updated'})
    return jsonify({'message': 'No fields updated or user not found'}), 400


@app.route('/users/<int:user_id>', methods=['DELETE'])
def delete_user_endpoint(user_id):
    deleted = delete_user(user_id)
    if deleted:
        return jsonify({'message': 'User deleted'})
    return jsonify({'message': 'User not found'}), 404

if __name__ == '__main__':
    create_table()
    app.run(debug=True)