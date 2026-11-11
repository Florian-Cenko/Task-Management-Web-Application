
export default function TaskFilter({
    searchTitle,
    onSearchChange,
    filterStatus,
    onStatusChange
}) {
    return (
        <div className="bg-white p-4 rounded-xl shadow-sm border border-gray-100 flex flex-col md:flex-row gap-4 mb-6">

            <input
                type="text"
                placeholder="Search by title..."
                value={searchTitle}
                onChange={e => onSearchChange(e.target.value)}
                className="flex-1 p-3 border border-gray-300 rounded-lg outline-none focus:ring-2 focus:ring-blue-500"
            />

            <select
                value={filterStatus}
                onChange={e => onStatusChange(e.target.value)}
                className="w-full md:w-48 p-3 border border-gray-300 rounded-lg bg-white"
            >
                <option value="">All Statuses</option>
                <option value="PENDING">PENDING</option>
                <option value="IN_PROGRESS">IN PROGRESS</option>
                <option value="COMPLETED">COMPLETED</option>
            </select>

        </div>
    );
}
