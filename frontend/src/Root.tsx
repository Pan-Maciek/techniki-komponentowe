import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { APP_ROUTES } from "./commons/routes";
import { Search } from "./views";

export const Root = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={APP_ROUTES.SEARCH} element={<Search />} />
      </Routes>
    </BrowserRouter>
  );
};
