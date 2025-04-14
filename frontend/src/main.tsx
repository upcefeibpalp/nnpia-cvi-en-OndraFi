import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { ThemeProvider } from "@mui/material/styles";

import './index.css'
import darkTheme from "../theme.ts";
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <ThemeProvider theme={darkTheme}>
          <App />
      </ThemeProvider>
  </StrictMode>,
)
