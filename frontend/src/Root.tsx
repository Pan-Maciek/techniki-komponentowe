import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { APP_ROUTES } from "./commons/routes";
import { muiTheme } from "./commons/muiTheme";
import { Search } from "./views";

export const Root = () => {
  return (
    <ThemeProvider theme={muiTheme}>
      <CssBaseline />
      <BrowserRouter>
        <Routes>
          <Route path={APP_ROUTES.SEARCH} element={<Search />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
};
