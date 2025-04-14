import { Container } from "@mui/material";
import UserCreateForm from "../components/UserCreateForm";
import AuthButton from "../components/AuthButton.tsx";

const Register = () => {
    return (
        <Container sx={{ mt: 4 }}>
            <AuthButton />

            <UserCreateForm />
        </Container>
    );
};

export default Register;
