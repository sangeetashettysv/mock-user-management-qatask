import { useEffect, useMemo, useState } from "react";

const UpdateUserModal = ({ selectedUser, updateUser }) => {
  const [formData, setFormData] = useState({
    id: "",
    name: "",
    username: "",
    email: "",
    phone: "",
    website: "",
  });

  const [errors, setErrors] = useState({
    phone: "",
  });

  useEffect(() => {
    if (!selectedUser) return;

    setFormData({
      id: selectedUser.id ?? "",
      name: selectedUser.name ?? "",
      username: selectedUser.username ?? "",
      email: selectedUser.email ?? "",
      phone: selectedUser.phone ?? "",
      website: selectedUser.website ?? "",
    });

    // reset validation whenever a new user is selected
    setErrors({ phone: "" });
  }, [selectedUser]);

  const validateField = (name, value) => {
    if (name === "phone") {
      if (!/^[0-9]{10}$/.test(value)) {
        return "Phone must be a 10-digit number (eg. 9812345678)";
      }
    }
    return "";
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    // update form data
    setFormData((prev) => ({ ...prev, [name]: value }));

    // validate only the changed field
    const error = validateField(name, value);
    setErrors((prev) => ({ ...prev, [name]: error }));
  };

  const canSubmit = useMemo(() => {
    // require phone non-empty and valid
    return formData.phone.length > 0 && !errors.phone;
  }, [formData.phone, errors.phone]);

  const handleUpdate = () => {
    if (!canSubmit) return;
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
            />
          </div>

          {/* Body */}
          <div className="modal-body">
            <input className="form-control mb-2" name="name" value={formData.name} disabled />
            <input className="form-control mb-2" name="username" value={formData.username} disabled />
            <input className="form-control mb-2" name="email" value={formData.email} disabled />

            <input
              type="tel"
              className={`form-control ${errors.phone ? "is-invalid" : ""}`}
              name="phone"
              required
              placeholder="Enter a 10-digit phone"
              pattern="[0-9]{10}"
              value={formData.phone}
              onChange={handleChange}
            />
            {errors.phone ? <div className="invalid-feedback">{errors.phone}</div> : null}

            <input className="form-control mt-2" name="website" value={formData.website} disabled />
          </div>

          {/* Footer */}
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
              Close
            </button>

            <button
              type="button"
              onClick={handleUpdate}
              className="btn btn-primary"
              data-bs-dismiss="modal"
              disabled={!canSubmit}
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
