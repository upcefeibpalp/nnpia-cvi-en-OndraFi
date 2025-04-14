import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { ThemeProvider } from "@mui/material/styles";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";

import './index.css'
import darkTheme from "../theme.ts";
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <Provider store={store}>
          <ThemeProvider theme={darkTheme}>
          <BrowserRouter>
              <App />
          </BrowserRouter>
      </ThemeProvider>
      </Provider>
  </StrictMode>,
)
