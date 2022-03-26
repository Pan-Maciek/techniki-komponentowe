import { ThemeOptions, createTheme } from "@mui/material";

const options: ThemeOptions = {
  palette: {
    mode: "dark",
    primary: {
      main: "#AC610C",
    },
    secondary: {
      main: "#008148",
    },
    text: {
      primary: "#F1E9DA",
      // secondary: "#AC610C",
    },
    background: {
      default: "#061C23",
      paper: "#031926",
    },
  },
  typography: {
    fontFamily: "Raleway",
  },
};

export const muiTheme = createTheme(options);
