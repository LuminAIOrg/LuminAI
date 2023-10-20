import requests
import json

def fetch_data_from_api(url):
    response = requests.get(url)
    data = response.json()
    return data

def get_production_data():
    pv_url = "http://192.168.1.110/solar_api/v1/GetPowerFlowRealtimeData.fcgi"
    meter_url = "http://192.168.1.110/solar_api/v1/GetMeterRealtimeData.cgi?Scope=System"

    pv_data = fetch_data_from_api(pv_url)
    meter_data = fetch_data_from_api(meter_url)

    production_data = [{
        "name": "PV",
        "value": pv_data["Body"]["Data"]["Site"]["P_PV"]
    },
    {
       "name": "Akku",
        "value": pv_data["Body"]["Data"]["Site"]["P_Akku"]
    }]

    entries = meter_data["Body"]["Data"]
    for entry_id, entry_data in entries.items():
        model = entry_data["Details"]["Model"]
        power_real_p_sum = entry_data["PowerReal_P_Sum"]
        production_data.append({"Model": model, "PowerReal_P_Sum": power_real_p_sum})

    return production_data

def get_consumption_data():
    url = "http://192.168.1.110/solar_api/v1/GetMeterRealtimeData.cgi?Scope=System"
    response = requests.get(url)
    data = response.json()
    consumption_data = {
        "name": "Consumer_1",
        "value": data["Body"]["Data"]["0"]["PowerReal_P_Sum"]
    }
    return consumption_data

def get_grid_data():
    url = "http://192.168.1.110/solar_api/v1/GetPowerFlowRealtimeData.fcgi"
    response = requests.get(url)
    data = response.json()
    grid_data = {
        "value": data["Body"]["Data"]["Site"]["P_Grid"]
    }
    return grid_data

# Create the JSON structure
json_data = {
    "production": get_production_data(),
    "consumption": [get_consumption_data()],
    "grid": get_grid_data()
}

# Print the formatted JSON
print(json.dumps(json_data, indent=4))
