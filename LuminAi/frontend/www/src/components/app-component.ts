import { html, render } from "lit-html";
import { store } from "Model/store";
import { distinctUntilChanged } from "rxjs";
import { router } from "../router/router";
import { Model } from "Model/Model";

class AppComponent extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: "open" });
  }
  connectedCallback() {
    store.pipe(distinctUntilChanged()).subscribe((model) => this.render(model));
  }
  private template(model: Model) {
    return html`
      
    `;
  }

  private render(model: Model) {
    render(this.template(model), this.shadowRoot);
  }
}
customElements.define("app-component", AppComponent);
