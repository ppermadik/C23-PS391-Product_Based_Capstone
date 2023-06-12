import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler

df = pd.read_csv('https://storage.googleapis.com/myritime_bucket/CakalangTunaNew.csv', sep=',', header=0, low_memory=False, index_col=['tanggal'])
df1 = df.copy()
scalers = {}

def get_index_column(column):
    index = df.columns.get_loc(column)
    return index

for i in df.columns:
    scaler = MinMaxScaler(feature_range=(0, 1))
    s_s = scaler.fit_transform(df1[i].values.reshape(-1, 1))
    s_s = np.reshape(s_s, len(s_s))
    scalers['scaler_' + i] = scaler
    df1[i] = s_s

def get_scaled_data():
    dataHarga = np.array(df1[:][:180])
    dataHargaReshaped = np.reshape(dataHarga, (1, 180, 27))
    return np.array(dataHargaReshaped)

def inverse_transform(prediction):
    for index, i in enumerate(df1.columns):
        scaler = scalers['scaler_' + i]
        prediction[:, :, index] = scaler.inverse_transform(prediction[:, :, index]).astype(int)
    return prediction
