import React from "react";
import { TextField, Button, Box, Typography, Snackbar, Alert } from "@mui/material";
import { useForm } from "react-hook-form";
import axios from "axios";
import { useDispatch } from "react-redux";
import { login } from "../authenticationSlice";
import { useNavigate } from "react-router-dom";

type LoginData = {
    email: string;
    password: string;
};

const UserLoginForm: React.FC = () => {
    const { register, handleSubmit } = useForm<LoginData>();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [error, setError] = React.useState(false);

    const onSubmit = async (data: LoginData) => {
        try {
            const response = await axios.post("http://localhost:9000/api/v1/auth/login", data);
            const token = response.data.token;
            console.log("JWT Token:", token);
            dispatch(login(token));
            navigate("/users"); // přesměrování po přihlášení
        } catch (err) {
            console.error("Chyba při přihlášení:", err);
            setError(true);
        }
    };

    return (
        <Box
            component="form"
            onSubmit={handleSubmit(onSubmit)}
            sx={{
                maxWidth: 400,
                mx: "auto",
                mt: 4,
                display: "flex",
                flexDirection: "column",
                gap: 2,
            }}
        >
            <Typography variant="h5" align="center">
                Přihlášení
            </Typography>

            <TextField label="Email" type="email" {...register("email")} fullWidth />
            <TextField label="Heslo" type="password" {...register("password")} fullWidth />

            <Button type="submit" variant="contained" fullWidth>
                Přihlásit se
            </Button>

            <Snackbar open={error} autoHideDuration={4000} onClose={() => setError(false)}>
                <Alert severity="error" onClose={() => setError(false)}>
                    Chybné přihlašovací údaje
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default UserLoginForm;
