from app import app, model
from flask import jsonify, request
import numpy as np
from app.models.scaler import get_scaled_data, inverse_transform, get_index_column

@app.route("/predict", methods=["POST"])
def pricePrediction():
    try:
        data = request.get_json()
        namaIkan = data['ikan']
        namaDaerah = data['daerah']
        namaKolom = namaIkan + "_" + namaDaerah
        feature = get_scaled_data()
        prediction = model.predict(feature)
        prediction = inverse_transform(prediction)
        index = get_index_column(namaKolom)
        data = {
            'namaKolom': namaKolom,
            'prediction': prediction[:,:,index].tolist()
        }
        # return jsonify(data)
        return jsonify(data)
    except Exception as e:
        error_message = str(e)
        return jsonify({"error": error_message}), 500
