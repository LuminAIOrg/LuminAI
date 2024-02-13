import {Data} from "@/types/Data";

export interface Sensor {
    id: number;
    name: string;
    unit: string | null;
    data: Data[]
}