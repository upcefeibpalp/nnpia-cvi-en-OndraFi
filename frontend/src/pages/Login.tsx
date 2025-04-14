import { Container } from "@mui/material";
import UserLoginForm from "../components/UserLoginForm";
import AuthButton from "../components/AuthButton.tsx";

const Login = () => (
    <Container sx={{ mt: 4 }}>
        <AuthButton />

        <UserLoginForm />
    </Container>
);

export default Login;
