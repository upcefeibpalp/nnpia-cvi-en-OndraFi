import {useEffect, useState} from "react";
import axios from "axios";
import {Container, Typography} from "@mui/material";
import UserTable from "../components/UserTable.tsx";
import UserCreateForm from "../components/UserCreateForm.tsx";

const UserList = () => {
    interface User {
        email: string,
        id: number,
        role: string,
        active: boolean
    }

    const [users, setUsers] = useState<User[]>([]);

    const toggleUserActive = (id: number, active: boolean) => {
        axios.put(`http://localhost:9000/api/v1/users/${id}/active/${active}`).then(response => {
            console.log("response", response);
            console.log("activated");
        })
        setUsers((prev: User[]) =>
            prev.map((user: User) =>
                user.id === id ? {...user, active: !user.active} : user
            )
        );
    };

    useEffect(() => {
        axios.get('http://localhost:9000/api/v1/users')
            .then(response => {
                console.log(response.data);
                setUsers(response.data);
            })
            .catch(e => {
                console.error('Chyba při načítání uživatelů:', e);
            });
    }, []);


    return (
        <Container>
            <Typography variant="h4" gutterBottom sx={{mt: 4}}>
                NNPIA - Single-page application
            </Typography>
            <UserTable users={users} toggleUserActive={toggleUserActive}/>
        </Container>
    );
}
export default UserList;