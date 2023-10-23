import requests
import json

def fetch_data_from_api(url):
    response = requests.get(url)
    data = response.json()
    return data

# Get energy_data
def get_energy_data():
    powerflow_url = "http://192.168.1.110/solar_api/v1/GetPowerFlowRealtimeData.fcgi"
    
    data = fetch_data_from_api(powerflow_url)

    # Create the JSON structure
    energy_data = {
        "devices":[
            {
                "name": "PV",
                "value": data["Body"]["Data"]["Site"]["P_PV"]
            },
            {
            "name": "Akku",
                "value": data["Body"]["Data"]["Site"]["P_Akku"]
            }
        ],
        "grid": data["Body"]["Data"]["Site"]["P_Grid"],
        "timestamp": data["Head"]["Timestamp"]
    }

    entries = data["Body"]["Data"]["SecondaryMeters"]
    for entry_id, entry_data in entries.items(): 
        energy_data["devices"].append({
            "name": entry_data["Label"], 
            "value": entry_data["P"]
        })

    return energy_data

energy_data = get_energy_data()

# Print the formatted JSON
#print(json.dumps(energy_data, indent=4))

# Write to JSON file
with open('energy_data.json', 'w', encoding='utf-8') as f:
    json.dump(energy_data, f, ensure_ascii=False, indent=4)