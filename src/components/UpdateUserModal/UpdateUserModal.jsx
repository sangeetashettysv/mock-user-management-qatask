import { useEffect, useState } from "react";

const UpdateUserModal = ({ selectedUser, updateUser }) => {
    const [formData, setFormData] = useState({
        name: "",
        username: "",
        email: "",
        phone: "",
        website: "",
    });

    useEffect(() => {
        if (selectedUser) {
            setFormData({
                name: selectedUser.name || "",
                username: selectedUser.username || "",
                email: selectedUser.email || "",
                phone: selectedUser.phone || "",
                website: selectedUser.website || "",
            });
        }
    }, [selectedUser]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleUpdate = () => {
        // Only phone will actually change, but we pass full object
        updateUser(formData);
    };

    return (
        <div id="updateUserModal" className="modal fade" tabIndex="-1">
            <div className="modal-dialog">
                <div className="modal-content">
                    {/* Header */}
                    <div className="modal-header">
                        <h5 className="modal-title">
                            Update {selectedUser?.name} (Id: {selectedUser?.id})
                        </h5>
                        <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                        ></button>
                    </div>

                    {/* Body */}
                    <div className="modal-body">
                        <input
                            className="form-control mb-2"
                            name="name"
                            value={formData.name}
                            disabled
                        />
                        <input
                            className="form-control mb-2"
                            name="username"
                            value={formData.username}
                            disabled
                        />
                        <input
                            className="form-control mb-2"
                            name="email"
                            value={formData.email}
                            disabled
                        />
                        <input
                            className="form-control mb-2"
                            name="phone"
                            value={formData.phone}
                            onChange={handleChange}
                            placeholder="Phone"
                        />
                        <input
                            className="form-control"
                            name="website"
                            value={formData.website}
                            disabled
                        />
                    </div>

                    {/* Footer */}
                    <div className="modal-footer">
                        <button
                            type="button"
                            className="btn btn-secondary"
                            data-bs-dismiss="modal"
                        >
                            Close
                        </button>
                        <button
                            type="button"
                            data-bs-dismiss="modal"
                            onClick={handleUpdate}
                            className="btn btn-primary"
                            disabled={!selectedUser}
                        >
                            Update Phone
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UpdateUserModal;