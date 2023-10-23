import { BehaviorSubject } from "rxjs";
import { Model } from "./Model";
import { produce } from "immer";

const initialState: Model = {};

const store = new BehaviorSubject<Model>(initialState);

export { store };
