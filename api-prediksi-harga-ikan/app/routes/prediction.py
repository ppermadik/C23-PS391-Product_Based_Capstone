from app import app, model
from flask import jsonify, request
import numpy as np
from app.models.fish import get_scaled_data, inverse_transform
    
@app.route("/fishprice", methods=["GET"])
def fishPricePrediction():
    try:
        feature = get_scaled_data()
        prediction = model.predict(feature)
        prediction = inverse_transform(prediction)
        data = {
            'status': 'success',
            'prediction': prediction[:,:,:].tolist()
        }
        return jsonify(data)
    except Exception as e:
        error_message = str(e)
        return jsonify({"error": error_message}), 500
