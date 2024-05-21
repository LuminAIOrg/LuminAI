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
    extend: {
      keyframes: {
        popUp: {
          '0%': {transform: 'scale(0.9) translateY(-30px)', opacity: 0},
          '100%': {transform: 'scale(1) translateY(0px)', opacity: 1},
        }
      },
      animation: {
        popUp: 'popUp 0.2s ease-in-out'
      },
      spacing: {
        '1/5': '20%'
      }
    },
  },
  plugins: [],
}

