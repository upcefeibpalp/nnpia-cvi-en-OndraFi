import React from "react";
import { Button } from "@mui/material";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../store";
import { logout } from "../authenticationSlice";
import { useNavigate } from "react-router-dom";

const AuthButton = () => {
    const token = useSelector((state: RootState) => state.auth.token);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleClick = () => {
        if (token) {
            dispatch(logout());
            navigate("/login");
        } else {
            navigate("/login");
        }
    };

    return (
        <Button variant="outlined" onClick={handleClick}>
            {token ? "Odhlásit se" : "Přihlásit se"}
        </Button>
    );
};

export default AuthButton;
