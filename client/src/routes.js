import React from "react";
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Login from './pages/Login';

export default function Routes() {
    return (
        <BrowserRouter>
        <Switch>
            <Route path="/" Component={Login}></Route>
        </Switch>
        </BrowserRouter>
    );
}