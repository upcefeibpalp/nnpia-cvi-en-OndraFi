import React, { useState } from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import {
    TextField,
    Button,
    Box,
    Typography,
    Snackbar,
    Alert,
} from "@mui/material";

type UserFormData = {
    email: string;
    password: string;
};

const UserCreateForm: React.FC = () => {
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
    } = useForm<UserFormData>();

    const [openSnackbar, setOpenSnackbar] = useState(false);

    const onSubmit = async (data: UserFormData) => {
        console.log("Form values:", data);

        try {
            const response = await axios.post("http://localhost:9000/api/v1/auth/signup", data);
            console.log("Uživatel vytvořen:", response.data);

            // Zobrazit hlášku a resetovat formulář
            setOpenSnackbar(true);
            reset();
        } catch (error) {
            console.error("Chyba při odesílání:", error);
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
            <Typography variant="h5" align="center" gutterBottom>
                Přidat uživatele
            </Typography>

            <TextField
                label="Email"
                type="email"
                {...register("email", {
                    required: "Email je povinný",
                    pattern: {
                        value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                        message: "Neplatný email",
                    },
                })}
                error={!!errors.email}
                helperText={errors.email?.message}
                fullWidth
            />

            <TextField
                label="Heslo"
                type="password"
                {...register("password", {
                    required: "Heslo je povinné",
                    minLength: {
                        value: 6,
                        message: "Heslo musí mít alespoň 6 znaků",
                    },
                })}
                error={!!errors.password}
                helperText={errors.password?.message}
                fullWidth
            />

            <Button variant="contained" type="submit" fullWidth>
                Vytvořit uživatele
            </Button>

            {/* Snackbar s úspěšnou hláškou */}
            <Snackbar
                open={openSnackbar}
                autoHideDuration={4000}
                onClose={() => setOpenSnackbar(false)}
                anchorOrigin={{ vertical: "top", horizontal: "center" }}
            >
                <Alert onClose={() => setOpenSnackbar(false)} severity="success" sx={{ width: "100%" }}>
                    Uživatel byl úspěšně vytvořen!
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default UserCreateForm;
