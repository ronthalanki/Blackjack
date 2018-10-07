#!flask/bin/python
from flask import Flask, jsonify, abort, make_response, request, url_for

app = Flask(__name__)

def simpleStrategy(hand):
    if (hand < 17):
        return 1
    return 0

#REST API Creation
@app.route('/blackjack/simple/<int:hand>', methods=['GET'])
def get_task(hand):
    return jsonify({'action': simpleStrategy(hand)})

if __name__ == '__main__':
    app.run(debug=True) 