
import { useState, useEffect } from "react";

export default function TaskEditForm({ task, onTaskUpdated, onCancel }) {
    const [title, setTitle] = useState(task.title || "");
    const [description, setDescription] = useState(task.description || "");
    const [status, setStatus] = useState(task.taskStatusEnum || "PENDING");

    useEffect(() => {
        setTitle(task.title || "");
        setDescription(task.description || "");
        setStatus(task.taskStatusEnum || "PENDING");
    }, [task]);

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!title.trim()) {
            alert("Title cannot be empty.");
            return;
        }

        onTaskUpdated({
            ...task,
            title: title.trim(),
            description: description.trim(),
            taskStatusEnum: status
        });
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-5">
            <h2 className="text-2xl font-bold text-gray-800">
                Edit Task
            </h2>

            <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                    Title
                </label>

                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="w-full p-3 border border-gray-300 rounded-lg outline-none focus:ring-2 focus:ring-blue-500"
                    placeholder="Enter task title"
                    required
                />
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                    Description
                </label>

                <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    rows="4"
                    className="w-full p-3 border border-gray-300 rounded-lg outline-none focus:ring-2 focus:ring-blue-500 resize-none"
                    placeholder="Enter task description"
                />
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                    Status
                </label>

                <select
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                    className="w-full p-3 border border-gray-300 rounded-lg outline-none focus:ring-2 focus:ring-blue-500 bg-white"
                >
                    <option value="PENDING">PENDING</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="COMPLETED">COMPLETED</option>
                </select>
            </div>

            <div className="flex justify-end gap-3 pt-2">
                <button
                    type="button"
                    onClick={onCancel}
                    className="px-5 py-3 rounded-lg border border-gray-300 text-gray-700 hover:bg-gray-100 transition"
                >
                    Cancel
                </button>

                <button
                    type="submit"
                    className="px-5 py-3 rounded-lg bg-blue-600 text-white hover:bg-blue-700 transition"
                >
                    Save Changes
                </button>
            </div>
        </form>
    );
}
