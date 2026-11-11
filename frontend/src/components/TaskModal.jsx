
export default function TaskModal({ isOpen, onClose, children }) {
    if (!isOpen) return null;

    return (
        <div
            className="fixed inset-0 z-50 flex items-center justify-center bg-gray-900/50 p-4"
            onClick={onClose}
        >
            <div
                className="bg-white p-8 rounded-2xl shadow-xl w-full max-w-lg relative"
                onClick={e => e.stopPropagation()}
            >
                <button
                    onClick={onClose}
                    className="absolute top-4 right-4 text-gray-400 hover:text-gray-700"
                >
                    ✕
                </button>

                {children}
            </div>
        </div>
    );
}
