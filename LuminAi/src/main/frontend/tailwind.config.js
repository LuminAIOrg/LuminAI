/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  content: [],
  theme: {
    fontFamily: {
      'montreux-branding': 'Montreux Branding',
      'bomber-escort-black': 'Bomber Escort Condensed',
      'bomber-escort-white': 'Bomber Escort Outline',
    },
    extend: {},
  },
  plugins: [],
}

