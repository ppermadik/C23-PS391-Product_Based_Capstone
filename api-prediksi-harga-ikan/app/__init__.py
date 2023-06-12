from flask import Flask
import tensorflow as tf

app = Flask(__name__)


model = tf.keras.models.load_model("model.h5")\

from app.routes import prediction
from app.models import scaler

