import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import AddUserModal from "../AddUserModal/AddUserModal";
import DeleteUserModal from "./../DeleteUserModal/DeleteUserModal";
import UpdateUserModal from "./../UpdateUserModal/UpdateUserModal"; 

const API_BASE = "https://jsonplaceholder.typicode.com";

// Cleans the phone number to only contain numbers and be 10 digits
const cleanPhone = (phone) => phone.replace(/[^0-9]/g, "").substring(0, 10);

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedUserId, setSelectedUserId] = useState(null);

  const selectedUser = users.find((user) => user.id === selectedUserId);

  const fetchUsers = async () => {
    try {
      const response = await fetch(API_BASE + "/users");
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      toast.error("An error occurred while fetching users");
    } finally {
      setLoading(false);
    }
  };

  const addUser = (values) => {
    const promise = fetch(API_BASE + "/users", {
      method: "POST",
      body: JSON.stringify(values),
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
    });

    toast.promise(promise, {
      loading: "Adding user...",
      success: (data) => {
        (async () => {
          const created = await data.json();
          setUsers((prev) => [...prev, created]);
        })();
        return "User added successfully";
      },
      error: "An error occurred while adding user",
    });
  };

  const deleteUser = () => {
    const deletedUserId = selectedUserId;
    const promise = fetch(`${API_BASE}/users/${deletedUserId}`, {
      method: "DELETE",
    });

    toast.promise(promise, {
      loading: "Deleting user...",
      success: () => {
        setUsers((prev) => prev.filter((user) => user.id !== deletedUserId));
        return "User deleted successfully";
      },
      error: "An error occurred while deleting user",
    });
  };

  // ✅ UPDATE USER
  const updateUser = (values) => {
    const userId = selectedUserId;

    const promise = fetch(`${API_BASE}/users/${userId}`, {
      method: "PUT", // or PATCH
      body: JSON.stringify(values),
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
    });

    toast.promise(promise, {
      loading: "Updating user...",
      success: (res) => {
        (async () => {
          const updated = await res.json();
          setUsers((prev) =>
            prev.map((u) => (u.id === userId ? { ...u, ...updated } : u))
          );
        })();
        return "User updated successfully";
      },
      error: "An error occurred while updating user",
    });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div className="container">
      {loading && <p>Loading users...</p>}

      {!loading && (
        <div className="row g-4">
          <div className="col col-md-2">
            <button
              type="button"
              className="btn btn-primary ms-auto d-md-block"
              data-bs-toggle="modal"
              data-bs-target="#addUserModal"
            >
              Add User
            </button>
          </div>

          <div className="col col-md-10">
            <h2>Users List</h2>
            {users.length == 0 && <p>No users available</p>}

            <table className="table table-hover table-responsive">
              <thead>
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Username</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone</th>
                  <th scope="col">Website</th>
                  <th scope="col">Actions</th>
                </tr>
              </thead>

              <tbody>
                {users.map((user) => (
                  <tr key={"email=" + user.email + "_id=" + user.id}>
                    <td scope="row">{user.name}</td>
                    <td>{user.username}</td>
                    <td>
                      <a className="text-reset" href={`mailto:${user.email}`} target="_blank">
                        {user.email}
                      </a>
                    </td>
                    <td>
                      <a className="text-reset" href={`tel:${cleanPhone(user.phone)}`} target="_blank">
                        {cleanPhone(user.phone)}
                      </a>
                    </td>
                    <td>
                      <a
                        className="text-reset"
                        href={user.website.startsWith("http") ? user.website : `http://${user.website}`}
                        target="_blank"
                      >
                        {user.website}
                      </a>
                    </td>

                    {/* Edit button */}
                    <td className="d-flex gap-3 align-items-center">
                      {/* Update button */}
                      <a
                        type="button"
                        data-bs-toggle="modal"
                        data-bs-target="#updateUserModal"
                        onClick={() => setSelectedUserId(user.id)}
                        title="Update"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          style={{ width: "24px", height: "24px" }}
                          className="text-primary"
                          fill="none"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                          strokeWidth={2}
                        >
                          <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L6.832 19.82a4.5 4.5 0 01-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 011.13-1.897L16.862 4.487z"
                          />
                        </svg>
                      </a>

                      {/* Delete button */}
                      <a
                        type="button"
                        data-bs-toggle="modal"
                        data-bs-target="#confirmDeleteModal"
                        onClick={() => setSelectedUserId(user.id)}
                        title="Delete"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          style={{ width: "24px", height: "24px" }}
                          className="text-danger"
                          fill="none"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                          strokeWidth={2}
                        >
                          <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
                          />
                        </svg>
                      </a>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}

      <AddUserModal addUser={addUser} />
      <DeleteUserModal deleteUser={deleteUser} selectedUser={selectedUser} />
      <UpdateUserModal updateUser={updateUser} selectedUser={selectedUser} />
    </div>
  );
};

export default UserManagement;